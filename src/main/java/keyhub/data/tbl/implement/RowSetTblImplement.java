package keyhub.data.tbl.implement;

import keyhub.data.DataValue;
import keyhub.data.tbl.Tbl;
import keyhub.data.tbl.operator.TblOperator;
import keyhub.data.tbl.operator.TblOperatorType;
import keyhub.data.tbl.join.TblJoin;
import keyhub.data.tbl.row.TblRow;
import keyhub.data.tbl.schema.TblColumnSchema;
import keyhub.data.tbl.schema.TblSchema;

import java.util.*;

public class RowSetTblImplement extends TblImplement implements DataValue {
    private final List<List<Object>> data;

    public RowSetTblImplement(TblSchema schema, List<List<Object>> data) {
        super(schema);
        this.data = data;
    }

    @Override
    public int count() {
        return data.size();
    }

    @Override
    public TblRow getRow(int index) {
        return TblRow.of(super.schema, data.get(index));
    }

    @Override
    public List<TblRow> getRows() {
        return data.stream()
                .map(row -> TblRow.of(super.schema, row))
                .toList();
    }

    @Override
    public TblJoin leftJoin(Tbl right) {
        return null;
    }

    @Override
    public TblJoin innerJoin(Tbl right) {
        return null;
    }

    @Override
    public Tbl select(String... columns) {
        List<TblColumnSchema> columnSchemas = new ArrayList<>();
        for (String column : columns){
            Optional<TblColumnSchema> schema = this.schema.findColumnSchema(column);
            if(schema.isEmpty()){
                throw new IllegalArgumentException("Column not found in schema");
            }
            columnSchemas.add(schema.get());
        }
        List<List<Object>> newData = new ArrayList<>();
        for(List<Object> row : this.data){
            List<Object> newRow = new ArrayList<>();
            for(TblColumnSchema<?> columnSchema : columnSchemas){
                int index = this.schema.getColumnIndex(columnSchema.getColumnName());
                newRow.add(row.get(index));
            }
            newData.add(newRow);
        }
        return new RowSetTblImplement(TblSchema.of(columnSchemas), newData);
    }

    @Override
    public Tbl where(String column, TblOperatorType operator, Object value) {
        TblOperator tblOperator = TblOperator.of(this);
        return tblOperator.by(column, operator, value);
    }

    @Override
    public List<Map<String, Object>> toRowMapList() {
        return List.of();
    }

    @Override
    public Map<String, List<Object>> toColumnListMap() {
        return Map.of();
    }
}
