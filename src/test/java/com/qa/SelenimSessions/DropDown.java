package com.qa.SelenimSessions;

public enum DropDown {

    INDEX {
        @Override
        public String toString() {
            return "Index";
        }
    },
    VALUE {
        @Override
        public String toString() {
            return "value";
        }
    },
    VISIBLETEXT {
        @Override
        public String toString() {
            return "visibletext";
        }
    }
}
