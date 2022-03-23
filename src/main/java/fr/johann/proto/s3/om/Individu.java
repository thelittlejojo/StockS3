package fr.johann.proto.s3.om;

import java.util.Objects;

/**
 * Classe représentant un individu et ses traits d'identité
 */
public class Individu {
    /** Nom de l'individu */
    protected String name;
    /** Prenom de l'individu */
    protected String firstName;

    public Individu(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individu individu = (Individu) o;
        return Objects.equals(name, individu.name) && Objects.equals(firstName, individu.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName);
    }
}
