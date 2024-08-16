package doma.sidim.util

import at.favre.lib.crypto.bcrypt.BCrypt
import doma.sidim.model.Products
import doma.sidim.model.Users
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object PreparedData {
    fun insertInitialData() {
        transaction {
            if (Users.select { Users.email eq "petr@petrov.com" }.empty()) {
                Users.insert {
                    it[firstname] = "Petr"
                    it[lastname] = "Petrov"
                    it[email] = "petr@petrov.com"
                    it[password] = BCrypt.withDefaults().hashToString(12, "123".toCharArray())
                }
            }

            val fruitsAndVegetables = listOf(
                ProductData(
                    "Apple",
                    "Crisp and sweet apples, perfect for snacking or adding to salads and desserts. Grown with care for exceptional taste and quality.",
                    "Orchard Fresh",
                    "https://images.unsplash.com/photo-1512578659172-63a4634c05ec?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    15
                ),
                ProductData(
                    "Banana",
                    "Fresh bananas packed with potassium and energy. Ideal for a quick snack or for adding to smoothies and baked goods.",
                    "Tropical Harvest",
                    "https://images.unsplash.com/photo-1676495706236-f28daeef95d3?q=80&w=1805&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    10
                ),
                ProductData(
                    "Carrot",
                    "Crunchy and nutritious carrots, rich in beta-carotene and vitamins. Perfect for salads, stews, or simply as a healthy snack.",
                    "Garden Fresh",
                    "https://images.unsplash.com/photo-1445282768818-728615cc910a?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    8
                ),
                ProductData(
                    "Tomato",
                    "Juicy and ripe tomatoes, ideal for salads, sauces, and sandwiches. Grown organically to ensure fresh taste and quality.",
                    "Vine-Ripe Organics",
                    "https://images.unsplash.com/photo-1518977822534-7049a61ee0c2?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    12
                ),
                ProductData(
                    "Potato",
                    "Versatile potatoes, great for boiling, baking, or frying. Rich in nutrients and essential for a variety of dishes.",
                    "Farmers' Choice",
                    "https://images.unsplash.com/photo-1698505949250-51f8b2c9c8c6?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    6
                ),
                ProductData(
                    "Orange",
                    "Sweet and tangy oranges, packed with vitamin C. Perfect for juicing, snacking, or adding to fruit salads.",
                    "Citrus Grove",
                    "https://images.unsplash.com/photo-1629550064380-5358f670321c?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    13
                ),
                ProductData(
                    "Spinach",
                    "Fresh spinach leaves, loaded with iron and antioxidants. Ideal for salads, smoothies, or cooking as a nutritious side dish.",
                    "Leafy Greens Co.",
                    "https://images.unsplash.com/photo-1547058606-7eb25508e7e0?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    9
                ),
                ProductData(
                    "Strawberry",
                    "Juicy strawberries with a perfect balance of sweetness and tartness. Ideal for desserts, smoothies, or snacking.",
                    "Berry Delight",
                    "https://images.unsplash.com/photo-1501626325795-f415649fd44e?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    18
                ),
                ProductData(
                    "Broccoli",
                    "Fresh broccoli florets, rich in vitamins and minerals. Perfect for steaming, roasting, or adding to stir-fries.",
                    "Green Harvest",
                    "https://images.unsplash.com/photo-1550409174-a8ea3586299c?q=80&w=1769&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    11
                ),
                ProductData(
                    "Grapes",
                    "Sweet and juicy grapes, perfect for snacking or adding to fruit salads and cheese platters. Seedless for easy eating.",
                    "Vineyard Select",
                    "https://images.unsplash.com/photo-1697365627432-c7a0050b626e?q=80&w=1775&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    20
                ),
                ProductData(
                    "Pepper",
                    "Crisp bell peppers available in various colors. Rich in vitamins and perfect for salads, stir-fries, and stuffing.",
                    "Colorful Harvest",
                    "https://images.unsplash.com/photo-1526346698789-22fd84314424?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    14
                ),
                ProductData(
                    "Onion",
                    "Flavorful onions, essential for cooking a wide variety of dishes. Can be used raw in salads or cooked in sauces.",
                    "Root Fresh",
                    "https://images.unsplash.com/photo-1720240462804-6b4216e1ac5e?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    5
                ),
                ProductData(
                    "Blueberry",
                    "Sweet and tart blueberries, high in antioxidants. Perfect for smoothies, desserts, or as a healthy snack.",
                    "Berrylicious Farms",
                    "https://images.unsplash.com/photo-1498557850523-fd3d118b962e?q=80&w=1769&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    22
                ),
                ProductData(
                    "Mango",
                    "Juicy and sweet mangoes with a rich, tropical flavor. Perfect for adding to smoothies, salads, or enjoying on their own.",
                    "Tropical Treats",
                    "https://images.unsplash.com/photo-1519096845289-95806ee03a1a?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    24
                ),
                ProductData(
                    "Pineapple",
                    "Fresh pineapple with a sweet and tangy flavor. Great for grilling, adding to fruit salads, or making tropical drinks.",
                    "Island Harvest",
                    "https://images.unsplash.com/photo-1685551637719-bab8f746027d?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    25
                ),
                ProductData(
                    "Zucchini",
                    "Versatile zucchini, perfect for grilling, sautéing, or adding to soups and stews. Low in calories and high in nutrients.",
                    "Garden Fresh",
                    "https://images.unsplash.com/photo-1687199126774-bc777f6d7c6e?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    10
                ),
                ProductData(
                    "Cucumber",
                    "Crisp cucumbers, ideal for salads, sandwiches, or pickling. Refreshing and hydrating, perfect for summer dishes.",
                    "HydroFarm Produce",
                    "https://images.unsplash.com/photo-1462536738427-0725f3eb98f7?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    9
                ),
                ProductData(
                    "Avocado",
                    "Creamy avocados, perfect for making guacamole or adding to salads and sandwiches. Rich in healthy fats and nutrients.",
                    "Green Valley",
                    "https://images.unsplash.com/photo-1693942276719-8eb2b97ecce1?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    30
                ),
                ProductData(
                    "Pear",
                    "Sweet and juicy pears, perfect for snacking or adding to desserts and salads. Grown with care for the best taste.",
                    "Orchard Fresh",
                    "https://images.unsplash.com/photo-1669748651255-debb2978766e?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    18
                ),
                ProductData(
                    "Watermelon",
                    "Refreshing watermelon with a sweet and juicy flavor. Perfect for hot summer days or as a healthy dessert option.",
                    "Summer Harvest",
                    "https://images.unsplash.com/photo-1637152305495-bff51206340c?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    40
                )
            )

            transaction {
                if (Products.selectAll().empty()) {
                    Products.batchInsert(fruitsAndVegetables) { product ->
                        this[Products.title] = product.title
                        this[Products.description] = product.description
                        this[Products.vendorInfo] = product.vendorInfo
                        this[Products.imageLink] = product.imageLink
                        this[Products.price] = product.price
                    }
                }
            }

        }
    }

    data class ProductData(
        val title: String,
        val description: String,
        val vendorInfo: String,
        val imageLink: String,
        val price: Long
    )
}
