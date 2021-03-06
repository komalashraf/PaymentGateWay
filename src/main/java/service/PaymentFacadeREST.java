/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mypayment.paymentgateway.Payment;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Komal
 */
@Stateless
@Path("com.mypayment.paymentgateway.payment")
public class PaymentFacadeREST extends AbstractFacade<Payment> {
    @PersistenceContext(unitName = "com.mypayment_PaymentGateWay_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PaymentFacadeREST() {
        super(Payment.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Payment entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Payment entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Payment find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Payment> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Payment> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("creditCardDetails")
    @Produces("text/plain")
    public String checkValidation() {
//        List<Payment> paymentList = new ArrayList<Payment>();
//                paymentList.add((Payment) super.findAll());
//        return String.valueOf(super.findAll());
        return String.valueOf(super.find(1));
    }
    
    @GET
    @Path("checkValidation/{cardNumber}/{securityNumber}/{availableAmount}")
    @Produces({"text/plain","application/xml", "application/json"})
    public Payment getValidation(@PathParam("cardNumber") String cardNumber, @PathParam("securityNumber") String securityNumber, @PathParam("availableAmount") double availableAmount){
       
      return super.checkValidation(cardNumber, securityNumber, availableAmount);
         
      //   return payment;
         }
    

//    @GET
//    @Path("findCardByNumber/{cardNumber}")
//    @Produces({"application/xml", "application/json"})
//    public boolean findCardByNumber(@PathParam("cardNumber") String cardNumber) {
//        return super.findCardByNumber(cardNumber);
//    }
    
    
}
