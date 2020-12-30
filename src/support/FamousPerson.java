package support;

import java.util.Comparator;

public class FamousPerson implements Comparable<FamousPerson> {
    protected String firstName, lastName, fact;
    protected int yearOfBirth;

    public FamousPerson(String firstName, String lastName, int yearOfBirth, String fact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fact = fact;
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;
        else {
            FamousPerson fp = (FamousPerson) obj;
            return (this.firstName.equals(fp.firstName) &&
                    this.lastName.equals(fp.lastName));
        }
    }

    @Override
    public String toString() {
        return (firstName + " " + lastName + " " + yearOfBirth + "：" + fact);
    }

    @Override
    public int compareTo(FamousPerson other) {
        if (!this.lastName.equals(other))
            return this.lastName.compareTo(other.lastName);
        else
            return this.firstName.compareTo(other.firstName);
    }

    public static Comparator<FamousPerson> yearOfBirthComparator() {
        return new Comparator<FamousPerson>() {
            @Override
            public int compare(FamousPerson element1, FamousPerson element2) {
                return (element1.yearOfBirth - element2.yearOfBirth);
            }
        };
    }
}
