package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import mate.academy.springboot.datajpa.dto.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.RequestMapperDto;
import mate.academy.springboot.datajpa.service.mapper.ResponseMapperDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final RequestMapperDto<CategoryRequestDto, Category> categoryRequestMapperDto;
    private final ResponseMapperDto<CategoryResponseDto, Category> categoryResponseMapperDto;

    public CategoryController(CategoryService categoryService,
                RequestMapperDto<CategoryRequestDto, Category> categoryRequestMapperDto,
                ResponseMapperDto<CategoryResponseDto, Category> categoryResponseMapperDto) {
        this.categoryService = categoryService;
        this.categoryRequestMapperDto = categoryRequestMapperDto;
        this.categoryResponseMapperDto = categoryResponseMapperDto;
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryService.save(
                categoryRequestMapperDto.toModel(requestDto));
        return categoryResponseMapperDto.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryRequestMapperDto.toModel(requestDto);
        category.setId(id);
        // ??? check
        categoryService.update(category);
        return categoryResponseMapperDto.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        return categoryResponseMapperDto.toDto(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
