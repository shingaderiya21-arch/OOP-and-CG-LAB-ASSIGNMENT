
class ChatUser extends Thread {

    private String userName;
    private String[] messages;
    private volatile boolean paused = false;
    private volatile boolean running = true;

    public ChatUser(String name, String[] messages) {
        this.userName = name;
        this.messages = messages;
    }

    // Suspend thread
    public void pauseChat() {
        paused = true;
    }

    // Resume thread
    public void resumeChat() {
        paused = false;
        synchronized (this) {
            notify();
        }
    }

    // Stop thread
    public void stopChat() {
        running = false;
        resumeChat(); // wake up if paused
    }

    @Override
    public void run() {
        try {
            for (String msg : messages) {
                synchronized (this) {
                    while (paused) {
                        wait(); // Wait until resumed
                    }
                }
                if (!running) {
                    break;
                }

                System.out.println(userName + " says: " + msg);
                Thread.sleep(1000); // Simulate typing delay
            }
        } catch (InterruptedException e) {
            System.out.println(userName + " was interrupted.");
        }
        System.out.println(userName + " has left the chat.");
    }
}

public class ChatSimulation {

    public static void main(String[] args) {
        // Messages for each user
        String[] user1Msgs = {"Hi!", "How are you?", "Let's meet tomorrow."};
        String[] user2Msgs = {"Hello!", "I'm good, thanks!", "Sure, see you then."};

        // Create user threads
        ChatUser user1 = new ChatUser("Pooja", user1Msgs);
        ChatUser user2 = new ChatUser("Anuj", user2Msgs);

        // Set priorities (Bob has urgent messages)
        user1.setPriority(Thread.NORM_PRIORITY);
        user2.setPriority(Thread.MAX_PRIORITY);

        // Start threads
        user1.start();
        user2.start();

        // Monitor threads
        try {
            Thread.sleep(1500);
            System.out.println("\nPausing Pooja for a while...");
            user1.pauseChat();

            Thread.sleep(3000);
            System.out.println("Resuming Pooja...");
            user1.resumeChat();

            Thread.sleep(2000);
            System.out.println("\nStopping Anuj early...");
            user2.stopChat();

            // Wait for both threads to finish
            user1.join();
            user2.join();

            // Check thread status
            System.out.println("\nIs Pooja alive? " + user1.isAlive());
            System.out.println("Is Anuj alive? " + user2.isAlive());
            System.out.println("Chat session ended.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
