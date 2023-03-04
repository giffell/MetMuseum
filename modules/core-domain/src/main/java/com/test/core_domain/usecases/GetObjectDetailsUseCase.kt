package com.test.core_domain.usecases

import com.test.common.Result
import com.test.common.network.AppCoroutineDispatchers
import com.test.common.network.Dispatcher
import com.test.core_domain.FlowUseCase
import com.test.core_domain.repositories.MetMuseumRepository
import com.test.core_model.ObjectDetailsInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetObjectDetailsUseCase @Inject constructor(
    @Dispatcher(AppCoroutineDispatchers.IO) private val coroutineDispatcher: CoroutineDispatcher,
    private val metMuseumRepository: MetMuseumRepository
) : FlowUseCase<GetObjectDetailsUseCase.Params, ObjectDetailsInfo>(coroutineDispatcher) {

    class Params(val objectId: Int)

    override fun execute(params: Params): Flow<Result<ObjectDetailsInfo>> {
        return metMuseumRepository.getObjectDetails(params.objectId).map {
            Result.Success(
                ObjectDetailsInfo(
                    objectID = it.objectID,
                    title = it.title,
                    primaryImage = it.primaryImage,
                    additionalImages = it.additionalImages,
                    department = it.department,
                    repository = it.repository,
                    culture = it.culture
                )
            )
        }
    }
}