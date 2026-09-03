package com.example.instantgarage.data.repository

import com.example.instantgarage.data.model.Mechanic
import com.example.instantgarage.data.remote.MechanicApi
import javax.inject.Inject

class MechanicRepository @Inject constructor(
    private val mechanicApi: MechanicApi
) {

    suspend fun getMechanics(): Result<List<Mechanic>> {
        return try {
            val response = mechanicApi.getMechanics()

            if (response.isSuccessful) {
                val mechanics = response.body()?.mechanics

                if (mechanics != null) {
                    Result.success(mechanics)
                } else {
                    Result.failure(Exception("Response body is empty"))
                }
            } else {
                Result.failure(
                    Exception("HTTP Error: ${response.code()} ${response.message()}")
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMechanicById(id: Int): Result<Mechanic> {
        return try {
            val response = mechanicApi.getMechanicById("mechanics[?(@.id==$id)]")

            if (response.isSuccessful) {
                val mechanics = response.body()

                if (!mechanics.isNullOrEmpty()) {
                    Result.success(mechanics.first())
                } else {
                    Result.failure(
                        Exception("HTTP Error: ${response.code()} ${response.message()}")
                    )
                }
            } else {
                Result.failure(Exception("HTTP Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}