buildscript {
    extra.apply {
        set("room_version", "2.6.1")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}
