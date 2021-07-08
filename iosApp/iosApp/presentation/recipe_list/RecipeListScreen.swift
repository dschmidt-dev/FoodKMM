//
//  RecipeListScreen.swift
//  iosApp
//
//  Created by Daniel Schmidt on 05.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // dependencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    private let foodCategories: [FoodCategory]
    
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(networkModule: self.networkModule, cacheModule: self.cacheModule)
        let foodCategoryUtil = FoodCategoryUtil()
        self.viewModel = RecipeListViewModel(searchRecipes: searchRecipesModule.searchRecipes, foodCategoryUtil: foodCategoryUtil)
        foodCategories = foodCategoryUtil.getAllFoodCategories()
    }
    
    var body: some View {
        VStack {
            HStack {
                Text("Page: \(viewModel.state.page), Size: \(viewModel.state.recipes.count)")
                    .padding()
            }
            SearchAppBar(
                query: viewModel.state.query,
                foodCateogires: foodCategories,
                onTriggerEvent: viewModel.onTriggerEvent
            )
            List {
                ForEach(viewModel.state.recipes, id: \.self.id) { recipe in
                    Text(recipe.title)
                        .onAppear(perform: {
                            if (viewModel.shouldQueryNextPage(recipe: recipe)) {
                                viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                            }
                        })
                }
            }
        }
    }
}

//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
