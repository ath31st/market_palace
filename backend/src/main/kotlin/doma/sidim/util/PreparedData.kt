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

            val kitchenProducts = listOf(
                ProductData(
                    "Chef's Knife",
                    "A high-quality chef's knife with a stainless steel blade that provides exceptional sharpness and durability. Ideal for slicing, dicing, and chopping a wide range of ingredients. Perfect for professional chefs and home cooks alike.",
                    "GourmetKnives Inc.",
                    "placeholder_image_link",
                    1200
                ),
                ProductData(
                    "Cutting Board",
                    "Durable and stylish cutting board made from premium bamboo. Provides a sturdy surface for cutting, chopping, and prepping ingredients. Gentle on knives and easy to clean, making it a kitchen essential.",
                    "EcoBoard Creations",
                    "placeholder_image_link",
                    600
                ),
                ProductData(
                    "Non-Stick Frying Pan",
                    "Premium non-stick frying pan designed for effortless cooking and easy cleanup. Features a heat-resistant handle and a reinforced non-stick coating that ensures food release and prevents sticking. Perfect for frying, sautéing, and searing.",
                    "CookMaster Ltd.",
                    "placeholder_image_link",
                    2000
                ),
                ProductData(
                    "Stainless Steel Pot",
                    "High-quality stainless steel pot with a 5-liter capacity. Ideal for boiling, simmering, and preparing soups and stews. Features a tempered glass lid and ergonomic handles for easy handling and pouring.",
                    "KitchenCraft Ltd.",
                    "placeholder_image_link",
                    2500
                ),
                ProductData(
                    "Electric Mixer",
                    "Powerful electric mixer with multiple speed settings and durable beaters. Ideal for mixing, whipping, and kneading dough. Ergonomic design with a comfortable grip and easy-to-clean parts.",
                    "MixPro Technologies",
                    "placeholder_image_link",
                    2800
                ),
                ProductData(
                    "Immersion Blender",
                    "Versatile immersion blender with a powerful motor and detachable blending shaft. Perfect for blending soups, sauces, and smoothies directly in the pot or bowl. Includes various attachments for different blending tasks.",
                    "BlendMaster Corp.",
                    "placeholder_image_link",
                    1500
                ),
                ProductData(
                    "Toaster",
                    "Compact and efficient toaster with multiple browning settings and a high-lift feature for easy removal of toasted items. Ideal for making perfect toast, bagels, and English muffins.",
                    "ToastTech Innovations",
                    "placeholder_image_link",
                    1400
                ),
                ProductData(
                    "Electric Kettle",
                    "Sleek and fast electric kettle with an automatic shut-off feature and a 1.7-liter capacity. Boils water quickly and safely with a concealed heating element and a convenient swivel base.",
                    "KettleWorks Inc.",
                    "placeholder_image_link",
                    2200
                ),
                ProductData(
                    "Spice Grinder",
                    "Manual spice grinder with a stainless steel blade and adjustable grind settings. Ideal for grinding spices, herbs, and coffee beans. Compact and easy to use, perfect for enhancing flavors in your dishes.",
                    "GrindMaster Co.",
                    "placeholder_image_link",
                    800
                ),
                ProductData(
                    "Digital Kitchen Scale",
                    "Precision digital kitchen scale with a large, easy-to-read display and tare function. Ideal for measuring ingredients accurately for cooking and baking. Features a sleek design and easy-to-clean surface.",
                    "ScaleTech Solutions",
                    "placeholder_image_link",
                    1300
                ),
                ProductData(
                    "Silicone Baking Mat",
                    "Reusable silicone baking mat perfect for baking cookies, pastries, and other treats. Provides a non-stick surface that makes baking and cleanup a breeze. Heat-resistant and easy to roll up for storage.",
                    "BakingEase Ltd.",
                    "placeholder_image_link",
                    900
                ),
                ProductData(
                    "Garlic Press",
                    "Heavy-duty garlic press with ergonomic handles and a durable stainless steel construction. Effortlessly crushes garlic cloves to release maximum flavor. Includes a built-in cleaner for easy maintenance.",
                    "GarlicMaster Co.",
                    "placeholder_image_link",
                    700
                ),
                ProductData(
                    "Rice Cooker",
                    "Convenient rice cooker with multiple cooking settings and a keep-warm function. Automatically cooks rice to perfection and keeps it warm until ready to serve. Ideal for busy kitchens.",
                    "RiceTech Innovations",
                    "placeholder_image_link",
                    2600
                ),
                ProductData(
                    "Coffee Maker",
                    "High-performance coffee maker with programmable timer and multiple brew strengths. Includes a built-in grinder for fresh coffee grounds and a carafe that keeps coffee hot for hours.",
                    "BrewMaster Corp.",
                    "placeholder_image_link",
                    3500
                ),
                ProductData(
                    "Ice Cream Maker",
                    "Home ice cream maker with a built-in compressor for making delicious ice cream, sorbet, and gelato. Features a user-friendly control panel and a large capacity bowl for batch preparation.",
                    "SweetTreats Ltd.",
                    "placeholder_image_link",
                    4500
                ),
                ProductData(
                    "Electric Can Opener",
                    "Efficient electric can opener with a smooth operation and ergonomic design. Easily opens cans of all sizes with a one-touch operation. Includes a built-in knife sharpener and a safety lid holder.",
                    "CanMaster Technologies",
                    "placeholder_image_link",
                    1100
                ),
                ProductData(
                    "Blender Bottle",
                    "Durable and leak-proof blender bottle with a stainless steel mixing ball. Ideal for mixing protein shakes, smoothies, and other beverages on the go. Features a convenient screw-on lid and ergonomic design.",
                    "MixItUp Inc.",
                    "placeholder_image_link",
                    500
                ),
                ProductData(
                    "Oven Mitts",
                    "Heat-resistant oven mitts with a quilted design and padded insulation. Provides a secure grip and protection from hot surfaces. Includes a convenient hanging loop for easy storage.",
                    "HeatGuard Creations",
                    "placeholder_image_link",
                    300
                ),
                ProductData(
                    "Meat Thermometer",
                    "Digital meat thermometer with instant read functionality and precise temperature measurement. Ideal for grilling, roasting, and cooking meats to perfection. Features a long probe and easy-to-read display.",
                    "CookPerfect Ltd.",
                    "placeholder_image_link",
                    1400
                ),
                ProductData(
                    "Pasta Maker",
                    "Manual pasta maker with adjustable rollers and a built-in cutter for making fresh pasta at home. Includes a variety of pasta shapes and thickness settings for customizing your pasta dishes.",
                    "PastaPro Co.",
                    "placeholder_image_link",
                    1900
                )
            )

            transaction {
                if (Products.selectAll().empty()) {
                    Products.batchInsert(kitchenProducts) { product ->
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
