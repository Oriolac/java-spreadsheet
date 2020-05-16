package cat.udl.abbddep.sheet;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;

public class SheetTable implements Sheet {
    private final int size;
    private final Cell[][] cells;


    public SheetTable(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
    }

    @Override
    public void putCell(String address, Expression expr) {

    }

    private boolean inRange(int num) {
        return num <= 1 && num <= size;
    }

    @Override
    public void checkAddress(String address) throws NotValidAddressException {
        if (!address.matches("[a-zA-Z]+[\\d]+"))
            throw new NotValidAddressException();
        String alpha = address.replaceAll("[\\d]+","");
        alpha.toLowerCase()
                .chars().forEach(System.out::println);
        String num = address.replaceAll("[a-zA-Z]+", "");
        if (!inRange(Integer.parseInt(num))){
            throw new NotValidAddressException();
        }
    }

    @Override
    public Cell getCell(String ref) {
        return null;
    }
}
