package main.java.configs;

/**
 *
 * @author jeprox
 */
public class Enums {

    public enum MangaNimeState {
        ongoing("ongoing"),
        completed("completed");
        private String state;

        private MangaNimeState(String state) {
            this.state = state;
        }

        public String getMangaNimeState() {
            return state;
        }
    }

    public enum MangaNimeIsWatchedRead {
        yes("1"),
        no("0");
        private String isWatchedRead;

        private MangaNimeIsWatchedRead(String isWatchedRead) {
            this.isWatchedRead = isWatchedRead;
        }

        public String getWatchedRead() {
            return isWatchedRead;
        }
    }
}
