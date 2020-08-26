package com.evlj.findmovie.domain.entities.paginated

sealed class PaginationCommand {
    object GetNext : PaginationCommand()
}