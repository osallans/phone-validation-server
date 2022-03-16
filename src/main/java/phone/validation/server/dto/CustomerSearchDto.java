package phone.validation.server.dto;

public class CustomerSearchDto {
    private int countryId;
    private int valid;
    private int pageIndex;
    private int pageSize;
    
    public CustomerSearchDto(){}
    public int getValid()
    {
        return valid;
    }
    public int getPageIndex()
    {
        return pageIndex;
    }
    public int getPageSize()
    {
        return pageSize;
    }
    public int getCountryId()
    {
        return countryId;
    }
    public void setValid(int valid)
    {
        this.valid=valid;
    }
    public void setPageIndex(int pageIndex)
    {
        this.pageIndex=pageIndex;
    }
    public void setPageSize(int pageSize)
    {
        this.pageSize=pageSize;
    }
    public void setCountryId(int countryId)
    {
        this.countryId=countryId;
    }

}
