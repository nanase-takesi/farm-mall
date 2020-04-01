package com.sheng.farm.mall.core.wrapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author takesi
 * @date 2020-01-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageWrapper<T> extends Wrapper<T> {

    private static final long serialVersionUID = 1391435130818161967L;

    private Long current;

    private Long pageSize;

    private Long total;

    /**
     * Instantiates a new Page wrapper.
     */
    PageWrapper() {
        super();
    }


    /**
     * Instantiates a new Page wrapper.
     *
     * @param code    the code
     * @param message the message
     */
    public PageWrapper(int code, String message) {
        super(code, message);
    }

    /**
     * Instantiates a new pageWrapper default code=200
     *
     * @param result   the result
     * @param current  the current
     * @param pageSize pageSize
     * @param total    total
     */
    public PageWrapper(T result, Long current, Long pageSize, Long total) {
        super();
        this.setData(result);
        this.current = current;
        this.pageSize = pageSize;
        this.total = total;
    }

    /**
     * Instantiates a new Page wrapper.
     *
     * @param code    the code
     * @param message the message
     * @param result  the result
     * @param current the  current
     */
    PageWrapper(int code, String message, T result, Long current, Long pageSize, Long total) {
        super(code, message, result);
        this.current = current;
        this.pageSize = pageSize;
        this.total = total;
    }

    /**
     * Sets the 分页数据 , 返回自身的引用.
     *
     * @param current the current
     * @return the page wrapper
     */
    public PageWrapper<T> pageUtil(Long current, Long pageSize, Long total) {
        this.current = current;
        this.pageSize = pageSize;
        this.total = total;
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param result the result
     * @return the page wrapper
     */
    @Override
    public PageWrapper<T> result(T result) {
        super.setData(result);
        return this;
    }

}
