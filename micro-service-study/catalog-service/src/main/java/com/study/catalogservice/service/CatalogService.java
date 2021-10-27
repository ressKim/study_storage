package com.study.catalogservice.service;

import com.study.catalogservice.jpa.CatalogEntity;

public interface CatalogService  {
    Iterable<CatalogEntity> getAllCatalogs();

}
