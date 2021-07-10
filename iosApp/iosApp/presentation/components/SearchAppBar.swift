//
//  SearchAppBar.swift
//  iosApp
//
//  Created by Daniel Schmidt on 08.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchAppBar: View {
    
    @State var query: String
    
    private let foodCateogires: [FoodCategory]
    private let selectedCategory: FoodCategory?
    private let onTriggerEvent: (RecipeListEvents) -> Void
    
    init(
        query: String,
        selectedCategory: FoodCategory?,
        foodCateogires: [FoodCategory],
         onTriggerEvent: @escaping (RecipeListEvents) -> Void
    ) {
        self.onTriggerEvent = onTriggerEvent
        self._query = State(initialValue: query)
        self.foodCateogires = foodCateogires
        self.selectedCategory = selectedCategory
    }
    
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "magnifyingglass")
                TextField(
                    "Search...",
                    text: $query,
                    onCommit: {
                        onTriggerEvent(RecipeListEvents.NewSearch())
                    }
                )
                .font(Font.custom("Comic Sans MS", size: 17))
                .onChange(of: query, perform: { value in
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(query: value))
                })
            }
            .padding(.bottom, 8)
            ScrollView(.horizontal) {
                HStack(spacing: 10) {
                    ForEach(foodCateogires, id: \.self) { category in
                        FoodCategoryChip(category: category.value, isSelected: selectedCategory == category)
                            .onTapGesture {
                                query = category.value
                                onTriggerEvent(RecipeListEvents.OnSelectCategory(category: category))
                            }
                    }
                }
            }
        }
        .padding(.leading, 8)
        .padding(.top, 8)
        .padding(.trailing, 8)
        .background(Color.white.opacity(0))
    }
}

//struct SearchAppBar_Previews: PreviewProvider {
//    static var previews: some View {
//        SearchAppBar()
//    }
//}
