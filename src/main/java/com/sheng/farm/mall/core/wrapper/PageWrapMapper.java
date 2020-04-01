package com.sheng.farm.mall.core.wrapper;

/**
 * @author takesi
 * @date 2020-01-15
 */
public class PageWrapMapper {

    /**
     * Instantiates a new page wrap mapper.
     */
    private PageWrapMapper() {
    }

    private static <E> PageWrapper<E> wrap(int code, String message, E o, Long current, Long pageSize, Long total) {
        return new PageWrapper<>(code, message, o, current, pageSize, total);
    }

    /**
     * Wrap data with default code=200.
     *
     * @param <E> the type parameter
     * @param o   the o
     * @return the page wrapper
     */
    public static <E> PageWrapper<E> wrap(E o, Long current, Long pageSize, Long total) {
        return wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o, current, pageSize, total);
    }

    /**
     * Wrap SUCCESS. code=200
     *
     * @param <E> the type parameter
     * @return the page wrapper
     */
    public static <E> PageWrapper<E> ok() {
        return new PageWrapper<>();
    }
}
