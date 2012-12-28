package com.plummersmind.bayesexample.services;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;

import org.granite.tide.data.DataEnabled;
import org.granite.tide.data.DataEnabled.PublishMode;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plummersmind.bayesexample.entities.Welcome;


@Service
@DataEnabled(topic="welcomeTopic", publish=PublishMode.ON_SUCCESS)
public class WelcomeServiceImpl implements WelcomeService {

    @PersistenceContext
    private EntityManager entityManager;
    

    @Transactional
    public Welcome hello(String name) {
        if (name == null || name.trim().length() == 0)
            throw new RuntimeException("Name cannot be null or empty");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Welcome welcome = null;
        try {
            Query q = entityManager.createQuery("select w from Welcome w where w.name = :name");
            q.setParameter("name", name);
            welcome = (Welcome)q.getSingleResult();
            welcome.setMessage("Welcome " + name + " (" + sdf.format(new Date()) + ")");
        }
        catch (NoResultException e) {
            welcome = new Welcome();
            welcome.setName(name);
            welcome.setMessage("Welcome " + name + " (" + sdf.format(new Date()) + ")");
            entityManager.persist(welcome);
        }
        return welcome;
    }
    
    
    @Transactional(readOnly=true)
    public List<Welcome> findAll() {
        return entityManager.createQuery("select w from Welcome w order by w.name", Welcome.class).getResultList();
    }
}
