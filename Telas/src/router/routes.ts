import { RouteConfig } from 'vue-router';

const routes: RouteConfig[] = [

    { path: '/', redirect: '/login' },
    {
        path: '/login',
        component: () => import('layouts/LoginLayout.vue'),
        children: [
            { path: '', component: () => import('src/pages/Login.vue') }
        ]
    },
    {
        path: '/home',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          { path: '', component: () => import('src/pages/Home.vue') }
        ]
    },

    // Always leave this as last one,
    // but you can also remove it
    {
        path: '*',
        component: () => import('pages/Error404.vue')
    }
];

export default routes;
