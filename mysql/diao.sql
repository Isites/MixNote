    select
        goods_id
    from
        temp_weekly_deal_order_stat os1
    where
        (
            select
                count(*)
            from
                temp_weekly_deal_order_stat os2
            where
                (os1.cat_id = os2.cat_id) and
                (os2.amount > os1.amount)
        ) < 60

