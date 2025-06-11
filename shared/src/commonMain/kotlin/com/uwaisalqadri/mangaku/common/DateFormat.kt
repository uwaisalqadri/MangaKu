package com.uwaisalqadri.mangaku.common

/**
 * Enum representing various date and time formatting patterns used throughout the application.
 *
 * Each constant provides a specific formatting pattern compatible with `java.time.format.DateTimeFormatter`
 * or `kotlinx.datetime` string formatting, intended for both parsing and displaying dates and times.
 */
enum class DateFormat(val pattern: String) {

    /**
     * Global ISO 8601 format with milliseconds and 'Z' suffix indicating UTC timezone.
     * Example: `2025-05-26T12:30:45.123Z`
     */
    DATE_TIME_GLOBAL("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),

    /**
     * Standard format without timezone and without milliseconds.
     * Example: `2025-05-26 12:30:45`
     */
    DATE_TIME_STANDARD("yyyy-MM-dd HH:mm:ss"),

    /**
     * Standard format with milliseconds.
     * Example: `2025-05-26 12:30:45.123`
     */
    DATE_TIME_STANDARD_WITH_MS("yyyy-MM-dd HH:mm:ss.SSS"),

    /**
     * Default short date format commonly used in Europe.
     * Example: `26.05.2025`
     */
    DEFAULT_PATTERN("dd.MM.yyyy"),

    /**
     * Default date and time format used for compact display.
     * Example: `26.05.2025 12:30`
     */
    DEFAULT_DATE_TIME_PATTERN("dd.MM.yyyy HH:mm"),

    /**
     * Display-friendly date format, suitable for concise views.
     * Example: `26 May 2025`
     */
    DISPLAY_PATTERN("dd MMM yyyy"),

    /**
     * Display-friendly date format, suitable for concise views.
     * Example: `26 May, 2025`
     */
    DISPLAY_PATTERN_COMMA("dd MMM, yyyy"),

    /**
     * Full month date format, often used in headers or summaries.
     * Example: `May 26, 2025`
     */
    MONTH_DATE_PATTERN("MMMM dd, yyyy"),

    /**
     * Time-only format (24-hour clock).
     * Example: `14:45`
     */
    DEFAULT_TIME_PATTERN("HH:mm"),

    /**
     * Format for showing dates in review or logs.
     * Example: `26 May 2025`
     */
    REVIEW_HISTORY_DATE_PATTERN("dd MMMM yyyy"),

    /**
     * Format including full weekday name and full month.
     * Example: `Monday, May 26 2025`
     */
    DAY_DATE_PATTERN("EEEE, MMMM dd yyyy")
}
