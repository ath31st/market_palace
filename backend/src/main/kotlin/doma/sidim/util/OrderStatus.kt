package doma.sidim.util

enum class OrderStatus(private val displayName: String) {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    SHIPPED("Shipped");

    fun displayName(): String {
        return this.displayName
    }

    companion object {
        fun getOrderStatus(orderIndex: Int): OrderStatus {
            return entries.getOrNull(orderIndex)
                ?: throw IllegalArgumentException("Invalid order status index: $orderIndex")
        }
    }
}
