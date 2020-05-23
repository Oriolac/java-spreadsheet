package cat.udl.abbddep.sheet;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;

public interface Sheet {

    public void putCell(String address, Expression expr) throws NotValidAddressException;

    public Cell getCell(String ref) throws NotValidAddressException;

    void clear();
}
