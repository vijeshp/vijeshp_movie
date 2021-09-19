package core.domain

data class Results(
    val display_title: String,
    val mpaa_rating: String,
    val critics_pick: Int,
    val byline: String,
    val headline: String,
    val summary_short: String,
    val publication_date: String,
    val opening_date: String,
    val date_updated: String,
    val link: Link,
    val multimedia: Multimedia
)

data class Multimedia(
    val type: String,
    val src: String,
    val height: Int,
    val width: Int
)

data class Link(
    val type: String,
    val url: String,
    val suggested_link_text: String
)

data class ApiResults(
    val status: String,
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Results>
)
