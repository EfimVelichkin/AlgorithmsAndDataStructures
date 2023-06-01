import java.util.Iterator;

public class ReveseLinkedList {

    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();
        contactList.addToEnd(new Contact(123, "Васильев Евстахий Борисович", "+123456789"));
        contactList.addToEnd(new Contact(183, "Величкин Аркадий Борисович", "555-555-555"));
        contactList.addToEnd(new Contact(153, "Полухин Евгений Егорович", "777-777-777"));
        contactList.addToEnd(new Contact(100, "Хахалев Егор Александрович", "22-22-22"));
        contactList.addToEnd(new Contact(127, "Карин Илья Евгеньевич", "8-800-555-35-35"));
        contactList.addToEnd(new Contact(137, "Богрев Кирилл Антонович", "85934442443"));

        for (Contact contact : contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("-------------------------");

        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                public boolean hasNext() {
                    return current != null;
                }

                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}