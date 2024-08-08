package doma.sidim.service

import doma.sidim.dto.Page
import doma.sidim.model.Product
import doma.sidim.repository.ProductRepository

class ProductService(private val productRepository: ProductRepository) {

    fun createProduct(product: Product): Long {
        return productRepository.create(product)
    }

    fun getProduct(id: Long): Product? {
        return productRepository.read(id)
    }

    fun updateProduct(id: Long, product: Product): Boolean {
        return productRepository.update(id, product)
    }

    fun deleteProduct(id: Long): Boolean {
        return productRepository.delete(id)
    }

    fun getProducts(page: Int, size: Int): Page<Product> {
        val offset = (page - 1) * size
        return productRepository.getPaged(offset, size)
    }
}
