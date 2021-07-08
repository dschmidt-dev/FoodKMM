//
//  FoodCategoryChip.swift
//  iosApp
//
//  Created by Daniel Schmidt on 08.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FoodCategoryChip: View {
    
    private let category: String
    private let isSelected: Bool
    
    init(category: String, isSelected: Bool = false) {
        self.category = category
        self.isSelected = isSelected
    }
    
    var body: some View {
        HStack {
            Text(category) //TODO update font
                .padding(8)
                .background(isSelected ? Color.gray : Color.blue) //TODO update the gray color
                .foregroundColor(Color.white)
        }
        .cornerRadius(10)
    }
}

struct FoodCategoryChip_Previews: PreviewProvider {
    static var previews: some View {
        FoodCategoryChip(category: "chicken", isSelected: true)
    }
}
