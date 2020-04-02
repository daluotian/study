package cn.idealismus.multiDataSource.config;

public enum DataSourceEnum {
    
    DB0("db0"),DB1("db1");
    
    private String value;
    
    DataSourceEnum (String value) {
        this.value = value;
    }
    
    public String getValue () {
        return value;
    }
}
