/* Copyright josivanSilva (Developer); 2018 */
package com.josivansilva.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.josivansilva.domain.CommonCustomer;
import com.josivansilva.domain.Customer;
import com.josivansilva.domain.PotentialCustomer;
import com.josivansilva.domain.SpecialCustomer;

/**
 * Customer repository class.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class CustomerRepository {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory ("josivansilva");
	
	/**
	 * Inserts a new customer.
	 * 
	 * @param customer the customer.
	 */
	public boolean insert (Customer customer) {
        boolean isInserted = false;
		// Create an EntityManager
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Save the special customer object
            manager.persist (customer);
            
            // Commit the transaction
            transaction.commit();
            isInserted = true;
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return isInserted;
    }
	
	/**
	 * Updates the existing customer.
	 * 
	 * @param customer the customer.
	 */
	public boolean update (Customer customer) {
        boolean isUpdated = false;
		// Create an EntityManager
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            if (customer instanceof SpecialCustomer) {
            	
            	// Get the Special customer object
                SpecialCustomer existentSpecialCustomer = manager.find (SpecialCustomer.class, customer.getCustomerId());
                existentSpecialCustomer.setCustomerName (customer.getCustomerName());
                existentSpecialCustomer.setCustomerType ("Special");
                existentSpecialCustomer.setCustomerRisk (customer.getCustomerRisk());
                existentSpecialCustomer.setCustomerMonthlyIncome (customer.getCustomerMonthlyIncome());
                existentSpecialCustomer.setCustomerAddress (customer.getCustomerAddress());
                existentSpecialCustomer.setCustomerTotalPatrimony (((SpecialCustomer) customer).getCustomerTotalPatrimony());
                
                // Update the Special customer object
                manager.persist (existentSpecialCustomer);
                
            } else if (customer instanceof PotentialCustomer) {
            	
            	// Get the Potential customer object
                PotentialCustomer existentPotentialCustomer = manager.find (PotentialCustomer.class, customer.getCustomerId());
                existentPotentialCustomer.setCustomerName (customer.getCustomerName());
                existentPotentialCustomer.setCustomerType ("Potential");
                existentPotentialCustomer.setCustomerRisk (customer.getCustomerRisk());
                existentPotentialCustomer.setCustomerMonthlyIncome (customer.getCustomerMonthlyIncome());
                existentPotentialCustomer.setCustomerAddress (customer.getCustomerAddress());
                existentPotentialCustomer.setCustomerCurrentDebts (((PotentialCustomer) customer).getCustomerCurrentDebts());
                
                // Update the Potential customer object
                manager.persist (existentPotentialCustomer);
            
            } else if (customer instanceof CommonCustomer) {
            	
            	// Get the Common customer object
                CommonCustomer existentCommonCustomer = manager.find (CommonCustomer.class, customer.getCustomerId());
                existentCommonCustomer.setCustomerName (customer.getCustomerName());
                existentCommonCustomer.setCustomerType ("Potential");
                existentCommonCustomer.setCustomerRisk (customer.getCustomerRisk());
                existentCommonCustomer.setCustomerMonthlyIncome (customer.getCustomerMonthlyIncome());
                existentCommonCustomer.setCustomerAddress (customer.getCustomerAddress());
                existentCommonCustomer. setCustomerEmployed (((CommonCustomer) customer).isCustomerEmployed());
                
                // Update the Common customer object
                manager.persist (existentCommonCustomer);
                
            }           

            // Commit the transaction
            transaction.commit();
            isUpdated = true;
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return isUpdated;
    }
	
	/**
	 * Deletes a Customer.
	 * 
	 * @param customer the customer.
	 */
	public boolean delete (Customer customer) {
        boolean isDeleted = false;
		// Create an EntityManager
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Customer object
            Customer customerToDelete = manager.find (Customer.class, customer.getCustomerId());

            // Delete the Customer
            manager.remove (customerToDelete);

            // Commit the transaction
            transaction.commit();
            isDeleted = true;
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return isDeleted;
	}
	
	/**
	 * Finds a customer by id.
	 * 
	 * @param customer the customer.
	 */
	public Customer findById (Customer customer) {
		Customer foundCustomer = null;
		// Create an EntityManager
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Customer object
            foundCustomer = manager.find (Customer.class, customer.getCustomerId());

            // Commit the transaction
            transaction.commit();
            
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return foundCustomer;
	}
	
	public List<Customer> findAll() {

        List<Customer> customerList = null;

        // Create an EntityManager
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Customers
            customerList = manager.createQuery ("SELECT obj FROM customer obj", Customer.class).getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return customerList;
    }
	
}
