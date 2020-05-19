package cat.udl.abbddep.sheet;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;

public class SheetTable implements Sheet {
    private final int size;
    private final Cell[][] cells;

    private class Address {
        private final int row;
        private final int column;

        public Address(String address) throws NotValidAddressException {
            if (!address.matches("[a-zA-Z]+[\\d]+"))
                throw new NotValidAddressException();
            row = getRow(address);
            column = getColumn(address);
            System.out.println(row);
            System.out.println(column);
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        private int getColumn(String address) throws NotValidAddressException {
            String alpha = address.replaceAll("[\\d]+", "");
            int[] chars = alpha.toLowerCase()
                    .chars()
                    .map(codePoint -> Character.getNumericValue(codePoint) - 10)
                    .toArray();
            int column = 0;
            for (int i = 0; i < chars.length; i++) {
                column += Math.pow(10, i) * chars[i];
            }
            if (notInRange(column))
                throw new NotValidAddressException();
            return column;
        }

        private int getRow(String address) throws NotValidAddressException {
            String num = address.replaceAll("[a-zA-Z]+", "");
            int row = Integer.parseInt(num);
            if (notInRange(Integer.parseInt(num))) {
                throw new NotValidAddressException();
            }
            return row - 1;
        }
    }

    public SheetTable(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
    }

    @Override
    public void putCell(String address, Expression expr) throws NotValidAddressException {
        Address ad = new Address(address);
        int row = ad.getRow();
        int num = ad.getColumn();
        cells[row][num] = new Cell(expr);
    }

    private boolean notInRange(int num) {
        return num < 0 || num > size;
    }


    @Override
    public Cell getCell(String ref) throws NotValidAddressException {
        Address ad = new Address(ref);
        int row = ad.getRow();
        int col = ad.getColumn();
        if (cells[row][col] == null)
            cells[row][col] = new Cell();
        return cells[row][col];
    }

}
