package epsi.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;
    private String prenom;
    private String sponsor;
    
    @Column(name = "points_fidelite")
    private Integer pointsFidelite;
    
    @Column(name = "numero_assurance_social")
    private String numeroAssuranceSocial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Integer getPointsFidelite() {
        return pointsFidelite != null ? pointsFidelite : 0;
    }

    public void setPointsFidelite(Integer pointsFidelite) {
        this.pointsFidelite = pointsFidelite;
    }

    public String getNumeroAssuranceSocial() {
        return numeroAssuranceSocial;
    }

    public void setNumeroAssuranceSocial(String numeroAssuranceSocial) {
        this.numeroAssuranceSocial = numeroAssuranceSocial;
    }
} 