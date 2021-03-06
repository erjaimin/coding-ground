package org.mcgill.ccs2_505.assignment02.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class TransactionSessionAttributeListener
 *
 */
@WebListener
public class TransactionSessionAttributeListener implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public TransactionSessionAttributeListener() {
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent ev)  { 
    	if(ev.getName().equals("bookTransaction")) {
    		System.out.println(ev.getValue());
    	}
    	if(ev.getName().equals("cdTransaction")){
    		System.out.println(ev.getValue());
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    }
	
}
