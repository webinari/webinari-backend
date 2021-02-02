package ru.webinari.web.core;


@FunctionalInterface
public interface ApiFunction<T> {

    T exec() throws ApiException;

}
