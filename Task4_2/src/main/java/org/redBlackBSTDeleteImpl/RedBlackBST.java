package org.redBlackBSTDeleteImpl;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class RedBlackNode {
        Key key;
        Value val;
        RedBlackNode left, right;
        boolean color;

        RedBlackNode(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private RedBlackNode root;

    // Метод для проверки красного цвета узла
    private boolean isRed(RedBlackNode x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // Поворот влево
    private RedBlackNode rotateLeft(RedBlackNode h) {
        if (h == null || h.right == null) return h; // Проверяем на null
        RedBlackNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // Поворот вправо
    private RedBlackNode rotateRight(RedBlackNode h) {
        if (h == null || h.left == null) return h; // Проверяем на null
        RedBlackNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // Переключение цветов у узлов
    private void flipColors(RedBlackNode h) {
        if (h == null || h.left == null || h.right == null) return; // Защита от NPE
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    // Удаление минимального элемента
    private RedBlackNode deleteMin(RedBlackNode h) {
        if (h == null) return null;
        if (h.left == null) return null; // Если левый потомок пуст, возвращаем null

        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h); // Применяем moveRedLeft, если левое поддерево не красное
        }

        h.left = deleteMin(h.left); // Рекурсивно удаляем минимальный элемент
        return balance(h); // Балансировка дерева после удаления
    }

    // Двигаем красную ссылку влево
    private RedBlackNode moveRedLeft(RedBlackNode h) {
        flipColors(h); // Меняем цвета
        if (isRed(h.right)) {
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Балансировка дерева
    private RedBlackNode balance(RedBlackNode h) {
        if (h == null) return null;

        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    // Вставка элемента
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private RedBlackNode put(RedBlackNode h, Key key, Value val) {
        if (h == null) return new RedBlackNode(key, val, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        // Балансировка после вставки
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    // Получение значения по ключу
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(RedBlackNode h, Key key) {
        while (h != null) {
            int cmp = key.compareTo(h.key);
            if (cmp < 0) {
                h = h.left;
            } else if (cmp > 0) {
                h = h.right;
            } else {
                return h.val;
            }
        }
        return null;
    }

    // Удаление максимального элемента
    public void deleteMax() {
        if (root == null) return;
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (root != null) root.color = BLACK;
    }

    private RedBlackNode deleteMax(RedBlackNode h) {
        if (h == null) return null;

        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null; // Если правого потомка нет, просто удаляем узел

        h.right = deleteMax(h.right); // Рекурсивно удаляем максимальный элемент
        return balance(h); // Балансировка дерева после удаления
    }
}
