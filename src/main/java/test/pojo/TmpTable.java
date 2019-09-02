package test.pojo;

public class TmpTable {
    private Integer id;

    private String colvarchar;

    private Boolean colbool;

    private Integer intcol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColvarchar() {
        return colvarchar;
    }

    public void setColvarchar(String colvarchar) {
        this.colvarchar = colvarchar == null ? null : colvarchar.trim();
    }

    public Boolean getColbool() {
        return colbool;
    }

    public void setColbool(Boolean colbool) {
        this.colbool = colbool;
    }

    public Integer getIntcol() {
        return intcol;
    }

    public void setIntcol(Integer intcol) {
        this.intcol = intcol;
    }
}