package cat.udl.abbddep.reference;

public class Reference {

    private final String address;

    public Reference(String address) throws NotValidReferenceAddressException {
        checkAddress(address);
        this.address = address;
    }

    private void checkAddress(String address) throws NotValidReferenceAddressException {
        if (!address.matches("[a-zA-Z]+[\\d]+"))
                throw new NotValidReferenceAddressException();
    }


}
