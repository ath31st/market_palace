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
                    "placeholder_image_link",
                    150
                ),
                ProductData(
                    "Banana",
                    "Fresh bananas packed with potassium and energy. Ideal for a quick snack or for adding to smoothies and baked goods.",
                    "Tropical Harvest",
                    "placeholder_image_link",
                    100
                ),
                ProductData(
                    "Carrot",
                    "Crunchy and nutritious carrots, rich in beta-carotene and vitamins. Perfect for salads, stews, or simply as a healthy snack.",
                    "Garden Fresh",
                    "placeholder_image_link",
                    80
                ),
                ProductData(
                    "Tomato",
                    "Juicy and ripe tomatoes, ideal for salads, sauces, and sandwiches. Grown organically to ensure fresh taste and quality.",
                    "Vine-Ripe Organics",
                    "placeholder_image_link",
                    120
                ),
                ProductData(
                    "Potato",
                    "Versatile potatoes, great for boiling, baking, or frying. Rich in nutrients and essential for a variety of dishes.",
                    "Farmers' Choice",
                    "placeholder_image_link",
                    60
                ),
                ProductData(
                    "Orange",
                    "Sweet and tangy oranges, packed with vitamin C. Perfect for juicing, snacking, or adding to fruit salads.",
                    "Citrus Grove",
                    "placeholder_image_link",
                    130
                ),
                ProductData(
                    "Spinach",
                    "Fresh spinach leaves, loaded with iron and antioxidants. Ideal for salads, smoothies, or cooking as a nutritious side dish.",
                    "Leafy Greens Co.",
                    "placeholder_image_link",
                    90
                ),
                ProductData(
                    "Strawberry",
                    "Juicy strawberries with a perfect balance of sweetness and tartness. Ideal for desserts, smoothies, or snacking.",
                    "Berry Delight",
                    "placeholder_image_link",
                    180
                ),
                ProductData(
                    "Broccoli",
                    "Fresh broccoli florets, rich in vitamins and minerals. Perfect for steaming, roasting, or adding to stir-fries.",
                    "Green Harvest",
                    "placeholder_image_link",
                    110
                ),
                ProductData(
                    "Grapes",
                    "Sweet and juicy grapes, perfect for snacking or adding to fruit salads and cheese platters. Seedless for easy eating.",
                    "Vineyard Select",
                    "placeholder_image_link",
                    200
                ),
                ProductData(
                    "Pepper",
                    "Crisp bell peppers available in various colors. Rich in vitamins and perfect for salads, stir-fries, and stuffing.",
                    "Colorful Harvest",
                    "placeholder_image_link",
                    140
                ),
                ProductData(
                    "Onion",
                    "Flavorful onions, essential for cooking a wide variety of dishes. Can be used raw in salads or cooked in sauces.",
                    "Root Fresh",
                    "placeholder_image_link",
                    50
                ),
                ProductData(
                    "Blueberry",
                    "Sweet and tart blueberries, high in antioxidants. Perfect for smoothies, desserts, or as a healthy snack.",
                    "Berrylicious Farms",
                    "placeholder_image_link",
                    220
                ),
                ProductData(
                    "Mango",
                    "Juicy and sweet mangoes with a rich, tropical flavor. Perfect for adding to smoothies, salads, or enjoying on their own.",
                    "Tropical Treats",
                    "placeholder_image_link",
                    240
                ),
                ProductData(
                    "Pineapple",
                    "Fresh pineapple with a sweet and tangy flavor. Great for grilling, adding to fruit salads, or making tropical drinks.",
                    "Island Harvest",
                    "placeholder_image_link",
                    250
                ),
                ProductData(
                    "Zucchini",
                    "Versatile zucchini, perfect for grilling, sautéing, or adding to soups and stews. Low in calories and high in nutrients.",
                    "Garden Fresh",
                    "placeholder_image_link",
                    100
                ),
                ProductData(
                    "Cucumber",
                    "Crisp cucumbers, ideal for salads, sandwiches, or pickling. Refreshing and hydrating, perfect for summer dishes.",
                    "HydroFarm Produce",
                    "placeholder_image_link",
                    90
                ),
                ProductData(
                    "Avocado",
                    "Creamy avocados, perfect for making guacamole or adding to salads and sandwiches. Rich in healthy fats and nutrients.",
                    "Green Valley",
                    "placeholder_image_link",
                    300
                ),
                ProductData(
                    "Pear",
                    "Sweet and juicy pears, perfect for snacking or adding to desserts and salads. Grown with care for the best taste.",
                    "Orchard Fresh",
                    "placeholder_image_link",
                    180
                ),
                ProductData(
                    "Watermelon",
                    "Refreshing watermelon with a sweet and juicy flavor. Perfect for hot summer days or as a healthy dessert option.",
                    "Summer Harvest",
                    "placeholder_image_link",
                    400
                )
            )

//            val kitchenProducts = listOf(
//                ProductData(
//                    "Chef's Knife",
//                    "A high-quality chef's knife with a stainless steel blade that provides exceptional sharpness and durability. Ideal for slicing, dicing, and chopping a wide range of ingredients. Perfect for professional chefs and home cooks alike.",
//                    "GourmetKnives Inc.",
//                    "placeholder_image_link",
//                    1200
//                ),
//                ProductData(
//                    "Cutting Board",
//                    "Durable and stylish cutting board made from premium bamboo. Provides a sturdy surface for cutting, chopping, and prepping ingredients. Gentle on knives and easy to clean, making it a kitchen essential.",
//                    "EcoBoard Creations",
//                    "placeholder_image_link",
//                    600
//                ),
//                ProductData(
//                    "Non-Stick Frying Pan",
//                    "Premium non-stick frying pan designed for effortless cooking and easy cleanup. Features a heat-resistant handle and a reinforced non-stick coating that ensures food release and prevents sticking. Perfect for frying, sautéing, and searing.",
//                    "CookMaster Ltd.",
//                    "placeholder_image_link",
//                    2000
//                ),
//                ProductData(
//                    "Stainless Steel Pot",
//                    "High-quality stainless steel pot with a 5-liter capacity. Ideal for boiling, simmering, and preparing soups and stews. Features a tempered glass lid and ergonomic handles for easy handling and pouring.",
//                    "KitchenCraft Ltd.",
//                    "placeholder_image_link",
//                    2500
//                ),
//                ProductData(
//                    "Electric Mixer",
//                    "Powerful electric mixer with multiple speed settings and durable beaters. Ideal for mixing, whipping, and kneading dough. Ergonomic design with a comfortable grip and easy-to-clean parts.",
//                    "MixPro Technologies",
//                    "placeholder_image_link",
//                    2800
//                ),
//                ProductData(
//                    "Immersion Blender",
//                    "Versatile immersion blender with a powerful motor and detachable blending shaft. Perfect for blending soups, sauces, and smoothies directly in the pot or bowl. Includes various attachments for different blending tasks.",
//                    "BlendMaster Corp.",
//                    "placeholder_image_link",
//                    1500
//                ),
//                ProductData(
//                    "Toaster",
//                    "Compact and efficient toaster with multiple browning settings and a high-lift feature for easy removal of toasted items. Ideal for making perfect toast, bagels, and English muffins.",
//                    "ToastTech Innovations",
//                    "placeholder_image_link",
//                    1400
//                ),
//                ProductData(
//                    "Electric Kettle",
//                    "Sleek and fast electric kettle with an automatic shut-off feature and a 1.7-liter capacity. Boils water quickly and safely with a concealed heating element and a convenient swivel base.",
//                    "KettleWorks Inc.",
//                    "placeholder_image_link",
//                    2200
//                ),
//                ProductData(
//                    "Spice Grinder",
//                    "Manual spice grinder with a stainless steel blade and adjustable grind settings. Ideal for grinding spices, herbs, and coffee beans. Compact and easy to use, perfect for enhancing flavors in your dishes.",
//                    "GrindMaster Co.",
//                    "placeholder_image_link",
//                    800
//                ),
//                ProductData(
//                    "Digital Kitchen Scale",
//                    "Precision digital kitchen scale with a large, easy-to-read display and tare function. Ideal for measuring ingredients accurately for cooking and baking. Features a sleek design and easy-to-clean surface.",
//                    "ScaleTech Solutions",
//                    "placeholder_image_link",
//                    1300
//                ),
//                ProductData(
//                    "Silicone Baking Mat",
//                    "Reusable silicone baking mat perfect for baking cookies, pastries, and other treats. Provides a non-stick surface that makes baking and cleanup a breeze. Heat-resistant and easy to roll up for storage.",
//                    "BakingEase Ltd.",
//                    "placeholder_image_link",
//                    900
//                ),
//                ProductData(
//                    "Garlic Press",
//                    "Heavy-duty garlic press with ergonomic handles and a durable stainless steel construction. Effortlessly crushes garlic cloves to release maximum flavor. Includes a built-in cleaner for easy maintenance.",
//                    "GarlicMaster Co.",
//                    "placeholder_image_link",
//                    700
//                ),
//                ProductData(
//                    "Rice Cooker",
//                    "Convenient rice cooker with multiple cooking settings and a keep-warm function. Automatically cooks rice to perfection and keeps it warm until ready to serve. Ideal for busy kitchens.",
//                    "RiceTech Innovations",
//                    "placeholder_image_link",
//                    2600
//                ),
//                ProductData(
//                    "Coffee Maker",
//                    "High-performance coffee maker with programmable timer and multiple brew strengths. Includes a built-in grinder for fresh coffee grounds and a carafe that keeps coffee hot for hours.",
//                    "BrewMaster Corp.",
//                    "placeholder_image_link",
//                    3500
//                ),
//                ProductData(
//                    "Ice Cream Maker",
//                    "Home ice cream maker with a built-in compressor for making delicious ice cream, sorbet, and gelato. Features a user-friendly control panel and a large capacity bowl for batch preparation.",
//                    "SweetTreats Ltd.",
//                    "placeholder_image_link",
//                    4500
//                ),
//                ProductData(
//                    "Electric Can Opener",
//                    "Efficient electric can opener with a smooth operation and ergonomic design. Easily opens cans of all sizes with a one-touch operation. Includes a built-in knife sharpener and a safety lid holder.",
//                    "CanMaster Technologies",
//                    "placeholder_image_link",
//                    1100
//                ),
//                ProductData(
//                    "Blender Bottle",
//                    "Durable and leak-proof blender bottle with a stainless steel mixing ball. Ideal for mixing protein shakes, smoothies, and other beverages on the go. Features a convenient screw-on lid and ergonomic design.",
//                    "MixItUp Inc.",
//                    "placeholder_image_link",
//                    500
//                ),
//                ProductData(
//                    "Oven Mitts",
//                    "Heat-resistant oven mitts with a quilted design and padded insulation. Provides a secure grip and protection from hot surfaces. Includes a convenient hanging loop for easy storage.",
//                    "HeatGuard Creations",
//                    "placeholder_image_link",
//                    300
//                ),
//                ProductData(
//                    "Meat Thermometer",
//                    "Digital meat thermometer with instant read functionality and precise temperature measurement. Ideal for grilling, roasting, and cooking meats to perfection. Features a long probe and easy-to-read display.",
//                    "CookPerfect Ltd.",
//                    "placeholder_image_link",
//                    1400
//                ),
//                ProductData(
//                    "Pasta Maker",
//                    "Manual pasta maker with adjustable rollers and a built-in cutter for making fresh pasta at home. Includes a variety of pasta shapes and thickness settings for customizing your pasta dishes.",
//                    "PastaPro Co.",
//                    "placeholder_image_link",
//                    1900
//                )
//            )

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
