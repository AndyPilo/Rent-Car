package cu.edu.cujae.bd.dto;

import java.sql.Date;

public class DateDto {
    private int codDate;
    private Date starDate;
    private Date finalDate;

    public DateDto(Date starDate,Date finalDate){
        this.starDate = starDate;
        this.finalDate = finalDate;
    }
    public DateDto(int codDate,Date starDate,Date finalDate){
        this.codDate = codDate;
        this.starDate = starDate;
        this.finalDate = finalDate;
    }

    public int getCodDate() {
        return codDate;
    }
    public void setCodDate(int codDate) {
        this.codDate = codDate;
    }
    public Date getStarDate() {
        return starDate;
    }
    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }
    public Date getFinalDate() {
        return finalDate;
    }
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    
}
