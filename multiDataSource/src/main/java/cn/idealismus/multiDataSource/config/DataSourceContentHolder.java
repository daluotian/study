package cn.idealismus.multiDataSource.config;

public class DataSourceContentHolder {
    public static final ThreadLocal<String> contentHolder = new InheritableThreadLocal<>();

    /**
     * 设置数据源
     * @param db
     */
    public static void setDataSource (String db) {
        contentHolder.set(db);
    }

    /**
     * 获取动迁数据源
     */
    public static String getDataSource () {
        return contentHolder.get();
    }

    /**
     * 清除上下文
     */
    public static void clearDataSource () {
        contentHolder.remove();
    }
}
