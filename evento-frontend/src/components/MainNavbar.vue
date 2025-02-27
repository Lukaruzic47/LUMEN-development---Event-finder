<script setup>
import {ref} from 'vue'
import logo from '../assets/EventioLogo.png'
import ProfileIcon from '../icons/profile.vue'
import SearchIcon from '../icons/search.vue'

function focusInput() {
  console.log('funkcija')
}

</script>
<template>
  <div class="h-24 bg-white w-full flex px-12 justify-between">
    <!-- Logo -->
    <div class="w-32 my-auto">
      <img :src="logo" class="h-16">
    </div>

    <!-- Search bar -->
    <div class="h-16 drop-shadow-lg w-[32em] bg-white rounded-full my-auto p-2 flex justify-between">
      <input type="text" class="w-[calc(100%-64px)] ml-6 text-lg text-gray-600 raleway-medium focus:outline-none"
             placeholder="Pretraži događaje, mjesta, organizatore...">
      <div @click="focusInput" class="hover:bg-red-600 cursor-pointer primary-purple w-12 h-12 rounded-full flex justify-center items-center">
        <SearchIcon class="text-white size-7"/>
      </div>
    </div>

    <!-- Profile icon -->
    <div class="w-32 my-auto">
      <ProfileIcon class="text-gray-600 size-12 float-right"/>
    </div>

  </div>
  <hr class="">
</template>
<style scoped>

</style>