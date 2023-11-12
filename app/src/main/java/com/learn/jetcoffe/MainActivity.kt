package com.learn.jetcoffe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learn.jetcoffe.model.BottomBarItem
import com.learn.jetcoffe.model.Menu
import com.learn.jetcoffe.model.dummyBestSellerMenu
import com.learn.jetcoffe.model.dummyCategory
import com.learn.jetcoffe.model.dummyMenu
import com.learn.jetcoffe.ui.components.CategoryItem
import com.learn.jetcoffe.ui.components.HomeSection
import com.learn.jetcoffe.ui.components.MenuItem
import com.learn.jetcoffe.ui.theme.JetCoffeTheme
import com.learn.jetcoffe.ui.components.Search
import com.learn.jetcoffe.ui.components.SectionText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { Bottombar() }
    ) {innerPadding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Banner()
            HomeSection(
                title = stringResource(R.string.section_category),
                content = { CategoryRow() }
            )
            HomeSection(
                title = stringResource(R.string.section_favorite_menu),
                content = { MenuRow(dummyMenu) }
            )
            HomeSection(
                title = stringResource(R.string.section_best_seller_menu),
                content = { MenuRow(dummyBestSellerMenu) }
            )
        }
    }
}
        //koemnt di bawah ini adlah contoh penerapan lambda dengan cara yang lain
//        HomeSection(stringResource(R.string.section_favorite_menu), Modifier, {
//            MenuRow(dummyMenu)
//        })
//        HomeSection(stringResource(R.string.section_best_seller_menu)){
//            MenuRow(dummyBestSellerMenu)
//        }
//    }
//}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
){
    Box(modifier = modifier){
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Coffe",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        Search()
    }
}

//memmbuat list  kategory
@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 5.dp),
        modifier = modifier
    ){
        items(dummyCategory, key = {it.textCategory }){ category ->
            CategoryItem(category)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryRowPreview(){
    JetCoffeTheme {
        CategoryRow()
    }
}

//membuat list menu
@Composable
fun MenuRow(
    listMenu : List<Menu>,
    modifier: Modifier = Modifier
    ){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(listMenu, key ={it.title}) {menu ->
            MenuItem(menu)
        }
    }
}

//membuat navigation bottom bar
@Composable
fun Bottombar(
    modifier: Modifier = Modifier
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            ),
        )
        navigationItems.map {
            NavigationBarItem(
                icon ={
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label ={
                    Text(it.title)
                },
                selected = it.title == navigationItems[0].title,
                onClick = {}
            )
        }
    }
}

//coba text field

@Composable
fun FormInput(){
    var name by remember { mutableStateOf("") }
    OutlinedTextField(
        value = name,
        onValueChange = {newname ->
            name = newname
        },
        label = { Text("name")},
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun FormInputPreview(){
    JetCoffeTheme {
        FormInput()
    }
}

//latihan state hosting



@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    JetCoffeTheme {
        JetCoffeeApp()
    }
}