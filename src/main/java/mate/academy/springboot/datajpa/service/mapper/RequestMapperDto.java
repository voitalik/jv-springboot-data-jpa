package mate.academy.springboot.datajpa.service.mapper;

public interface RequestMapperDto<D, T> {
    T toModel(D requestDto);
}
