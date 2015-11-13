/**
 *		                     /$$        /$$$$$$  /$$
 *		  /$$$$$$$  /$$$$$$ | $$$$$$$ |__/  \ $$ /$$
 *		 /$$_____/ |____  $$| $$__  $$  /$$$$$$/| $$
 *		|  $$$$$$   /$$$$$$$| $$  \ $$ /$$____/ | $$
 *		 \____  $$ /$$__  $$| $$  | $$| $$      | $$
 *		 /$$$$$$$/|  $$$$$$$| $$$$$$$/| $$$$$$$$| $$
 *		|_______/  \_______/|_______/ |________/|__/                         
 *                          ~ Departement - SSI
 *                           ~ @author martinsa 
 * 
 */
package com.sab2i.demo.camel.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sab_quote")
@NamedQueries({
    @NamedQuery(name = "quote.listAll", query = "SELECT q FROM Quote q"),
    @NamedQuery(name = "quote.findById", query = "SELECT q FROM Quote q WHERE q.id = :id"),
    @NamedQuery(name = "quote.findBySymbol", query = "SELECT q FROM  Quote q WHERE q.name = :symbol")
})
public class Quote {

    @Id
    @SequenceGenerator(name = "gen_quote", sequenceName = "seq_quote")
    @GeneratedValue(generator = "gen_quote")
    private long id;
    @Column(name = "quote_name")
    private String name;
    @OneToMany
    @JoinTable(name = "sab_quote_role")
    private List<Role> authorizedRoles;
    
    public Quote(){
        this.authorizedRoles = new ArrayList<>();
    }
    
    public Quote(String name, List<Role> authorRoles) {
        this.name = name;
        this.authorizedRoles = authorizedRoles;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the authorizedRoles
     */
    public List<Role> getAuthorizedRoles() {
        return authorizedRoles;
    }

    /**
     * @param authorizedRoles the authorizedRoles to set
     */
    public void setAuthorizedRoles(List<Role> authorizedRoles) {
        this.authorizedRoles = authorizedRoles;
    }
    
    public void addAuthorizedRole(Role role) {
        this.authorizedRoles.add(role);
    }
    
    public void removeAuthorizedRole(Role role) {
        this.authorizedRoles.remove(role);
    }
}
