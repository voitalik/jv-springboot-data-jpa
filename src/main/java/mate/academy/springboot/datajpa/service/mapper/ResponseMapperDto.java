package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseMapperDto<D, T> {
    D toDto(T model);
}
