 
package org.java.dao;

import java.io.IOException;
import java.util.Date;
 
import javax.persistence.NoResultException;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.java.entity.Item;
import org.java.form.ItemForm;
import org.java.model.ItemInfo;
import org.java.util.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
@Transactional
@Repository
public class ItemD {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public Item findProduct(String code) {
        try {
            String sql = "Select e from " + Item.class.getName() + " e Where e.code =:code ";
 
            Session session = this.sessionFactory.getCurrentSession();
            Query<Item> query = session.createQuery(sql, Item.class);
            query.setParameter("code", code);
            return (Item) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
 
    public ItemInfo findProductInfo(String code) {
        Item product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ItemInfo(product.getCode(), product.getName(), product.getPrice());
    }
 
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(ItemForm productForm) {
 
        Session session = this.sessionFactory.getCurrentSession();
        String code = productForm.getCode();
 
        Item product = null;
 
        boolean isNew = false;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            isNew = true;
            product = new Item();
            product.setCreateDate(new Date());
        }
        product.setCode(code);
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
 
        if (productForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = productForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }
        if (isNew) {
            session.persist(product);
        }
        // If error in DB, Exceptions will be thrown out immediately
        session.flush();
    }
 
    public PaginationResult<ItemInfo> queryItems(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + ItemInfo.class.getName() //
                + "(p.code, p.name, p.price) " + " from "//
                + Item.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        // 
        Session session = this.sessionFactory.getCurrentSession();
        Query<ItemInfo> query = session.createQuery(sql, ItemInfo.class);
 
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ItemInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<ItemInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryItems(page, maxResult, maxNavigationPage, null);
    }
 
}