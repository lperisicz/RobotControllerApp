package hr.perisic.luka.data.di

import hr.perisic.luka.data.api.converter.PGMImageConverter
import hr.perisic.luka.data.api.converter.PGMImageConverterImpl
import hr.perisic.luka.data.api.converter.strategy.PGMConverterStrategy
import hr.perisic.luka.data.api.converter.strategy.PGMP2Strategy
import hr.perisic.luka.data.api.converter.strategy.PGMP5Strategy
import org.koin.core.qualifier.TypeQualifier
import org.koin.dsl.module

internal val converterModule = module {

    single<PGMImageConverter> {
        PGMImageConverterImpl(
            p2Strategy = get(qualifier = TypeQualifier(PGMP2Strategy::class)),
            p5Strategy = get(qualifier = TypeQualifier(PGMP5Strategy::class))
        )
    }

    single<PGMConverterStrategy>(qualifier = TypeQualifier(PGMP2Strategy::class)) {
        PGMP2Strategy()
    }


    single<PGMConverterStrategy>(qualifier = TypeQualifier(PGMP5Strategy::class)) {
        PGMP5Strategy()
    }
}