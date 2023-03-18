package model.storage;

/**
 * Interface for handling a group of products.
 *
 * @author leontiev
 */
public interface IProductGroup {
        /**
         * Adds product to the group.
         *
         * @param product the product to be added.
         */
        void addProduct(Product product);

        /**
         * Removes an amount of a certain product from the group.
         *
         * @param productName name of the product to be removed.
         * @param amount      amount to be removed.
         * @return            the removed product.
         */
        Product removeProduct(String productName, int amount);

        /**
         * Finds a product based on its name.
         *
         * @param productName name of the product to be found.
         * @return            the found product.
         */
        Product findProduct(String productName);

        /**
         * Deletes all the product.
         *
         * @param productName the name of the product to be deleted.
         */
        void deleteProduct(String productName);
}
