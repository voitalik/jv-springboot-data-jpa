package mate.academy.springboot.datajpa.controller;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestMapperDto;
import mate.academy.springboot.datajpa.service.mapper.ResponseMapperDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final RequestMapperDto<ProductRequestDto, Product> productRequestMapperDto;
    private final ResponseMapperDto<ProductResponseDto, Product> productResponseMapperDto;

    public ProductController(ProductService productService,
                RequestMapperDto<ProductRequestDto, Product> productRequestMapperDto,
                ResponseMapperDto<ProductResponseDto, Product> productResponseMapperDto) {
        this.productService = productService;
        this.productRequestMapperDto = productRequestMapperDto;
        this.productResponseMapperDto = productResponseMapperDto;
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productService.save(productRequestMapperDto.toModel(requestDto));
        return productResponseMapperDto.toDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productRequestMapperDto.toModel(requestDto);
        product.setId(id);
        return productResponseMapperDto.toDto(productService.update(product));
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return productResponseMapperDto.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getAllBetweenTwoValues(@RequestParam BigDecimal from,
                                                           @RequestParam BigDecimal to) {
        return productService.getAllBetweenTwoValues(from, to).stream()
                .map(productResponseMapperDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    public Map<Category, List<Product>> findAllInCategories(
            @RequestParam Map<String, String> categories) {
        return productService.findAllInCategories(categories);
    }
}
