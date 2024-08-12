package com.sbitbd.farmerassist.di;

import com.sbitbd.farmerassist.Repository.FirestoreRepository;
import com.sbitbd.farmerassist.Repository.FirestoreRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class FirestoreBindings {
    @Binds
    abstract FirestoreRepository bindFirestoreRepository(FirestoreRepositoryImpl repository);
}
