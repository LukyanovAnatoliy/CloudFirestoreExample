package lav.cloudfirestoreexample.data.remote

import com.google.firebase.Timestamp

/**
 * Created by Anatoliy Lukyanov on 10/03/2019.
 *
 */
data class RemoteTask(
    var id: String = "",
    var title: String = "",
    var created: Timestamp = Timestamp(0, 0)
)